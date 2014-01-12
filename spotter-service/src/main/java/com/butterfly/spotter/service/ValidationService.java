package com.butterfly.spotter.service;

import com.butterfly.spotter.dao.SpotterDao;
import com.butterfly.spotter.model.CallerDbObject;
import com.butterfly.spotter.model.LoginObject;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Objects;

/**
 * @author : Nadim
 * @since : 1/11/14
 */
public class ValidationService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private SpotterDao spotterDao;

    @Inject
    public ValidationService(SpotterDao spotterDao) {
        this.spotterDao = spotterDao;
    }

    public boolean validateSubject(LoginObject loginObject) {
        CallerDbObject callerObj = spotterDao.getCallerObject(loginObject.getCallerId());

        if (callerObj == null) {
            logger.error("Caller Object not found for callerId", loginObject.getCallerId());
           return false;
        }
        validatePasswordPolicy(loginObject.getPassword(), callerObj.getHashPassword());
        return true;
    }

    private boolean validatePasswordPolicy(String password, String hashPassword) {
        //TODO: more sofisticating checking is required
        password = Hashing.md5().hashString(password, Charsets.UTF_8).toString();

        if (!Objects.equals(password, hashPassword)) {
            return false;
        }
        return true;
    }
}
