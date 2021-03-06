package com.payneteasy.superfly.spring;

import org.springframework.beans.factory.annotation.Required;

import com.payneteasy.superfly.dao.UserDao;
import com.payneteasy.superfly.password.PasswordGenerator;
import com.payneteasy.superfly.password.UserPasswordEncoder;
import com.payneteasy.superfly.policy.account.AccountPolicy;
import com.payneteasy.superfly.policy.account.none.SimpleAccountPolicy;
import com.payneteasy.superfly.policy.account.pcidss.PCIDSSAccountPolicy;
import com.payneteasy.superfly.resetpassword.ResetPasswordStrategy;

/**
 * Factory bean for {@link AccountPolicy}.
 *
 * @author Roman Puchkovskiy
 */
public class AccountPolicyFactoryBean extends
        AbstractPolicyDependingFactoryBean<AccountPolicy> {

    private UserDao userDao;

    private AccountPolicy accountPolicy;
    private UserPasswordEncoder userPasswordEncoder;
    private PasswordGenerator passwordGenerator;
    private ResetPasswordStrategy resetPasswordStrategy;

    @Required
    public void setResetPasswordStrategy(ResetPasswordStrategy resetPasswordStrategy) {
        this.resetPasswordStrategy = resetPasswordStrategy;
    }

    @Required
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Required
    public void setUserPasswordEncoder(UserPasswordEncoder userPasswordEncoder) {
        this.userPasswordEncoder = userPasswordEncoder;
    }

    @Required
    public void setPasswordGenerator(PasswordGenerator passwordGenerator) {
        this.passwordGenerator = passwordGenerator;
    }

    public AccountPolicy getObject() throws Exception {
        if (accountPolicy == null) {
            Policy policy = findPolicyByIdentifier();
            switch (policy) {
            case NONE:
                SimpleAccountPolicy simpleAccountPolicy = new SimpleAccountPolicy();
                simpleAccountPolicy.setUserDao(userDao);
                accountPolicy = simpleAccountPolicy;
                break;
            case PCIDSS:
                PCIDSSAccountPolicy pcidssAccountPolicy = new PCIDSSAccountPolicy();
                pcidssAccountPolicy.setUserDao(userDao);
                pcidssAccountPolicy.setUserPasswordEncoder(userPasswordEncoder);
                pcidssAccountPolicy.setPasswordGenerator(passwordGenerator);
                pcidssAccountPolicy.setResetPasswordStrategy(resetPasswordStrategy);
                accountPolicy = pcidssAccountPolicy;
                break;
            default:
                throw new IllegalStateException("Unknown policy: " + policy);
            }
        }
        return accountPolicy;
    }

    public Class<?> getObjectType() {
        return AccountPolicy.class;
    }

    public boolean isSingleton() {
        return true;
    }

}
