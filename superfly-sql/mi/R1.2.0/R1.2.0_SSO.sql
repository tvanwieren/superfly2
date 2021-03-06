call run_install_command('alter table users add column name varchar(32) not null', '42S21');
call run_install_command('alter table users add column surname varchar(32) not null', '42S21');
call run_install_command('alter table users add column secret_question varchar(255) not null', '42S21');
call run_install_command('alter table users add column secret_answer varchar(255) not null', '42S21');
call run_install_command('alter table users modify column user_password varchar(128) not null', '42S21');
call run_install_command('alter table users add column salt varchar(64) null', '42S21');
call run_install_command('alter table unauthorised_access drop column printed_password', '42S21');

drop table if exists user_history;
create table user_history(
  `user_user_id`         int(10) not null,
  `user_password` varchar(128) not null,
  `salt` varchar(64) not null,
  `number_history` int(10) unsigned NOT NULL,                                                                           
  `start_date` datetime DEFAULT NULL,                                                                                   
  `end_date` datetime DEFAULT NULL,                                                                                     
  `update_date` datetime DEFAULT NULL,                                                                                  
  PRIMARY KEY (`user_user_id`,`number_history`),                                                                        
  KEY `idx_user_history_start_end_dates` (`user_user_id`,`start_date`,`end_date`),
  CONSTRAINT `fk_user_history_users` FOREIGN KEY (`user_user_id`) REFERENCES `users` (`user_id`)
) engine = innodb;


call run_install_command('alter table users add column hotp_counter int(11) not null default 0', '42S21');
call run_install_command('alter table users add column hotp_salt varchar(64) not null', '42S21');
call run_install_command('alter table users add column hotp_logins_failed int(10) default 0', '42S21');

call run_install_command('alter table users add column is_password_temp varchar(1) not null default "N"', '42S21');
call run_install_command('alter table users add column create_date datetime not null', '42S21');
call run_install_command('alter table users add column is_account_suspended varchar(1) not null default "N"', '42S21');
