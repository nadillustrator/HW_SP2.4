package pro.sky.checkingUserRegistration.service;

import org.springframework.stereotype.Service;
import pro.sky.checkingUserRegistration.exceptions.DifferentPasswordsException;
import pro.sky.checkingUserRegistration.exceptions.WrongLoginException;
import pro.sky.checkingUserRegistration.exceptions.WrongPasswordExeption;

@Service
public class CheckUserInfoServiceImpl implements CheckUserInfoService {

    private final String allowedValues = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_";

    @Override
    public boolean isRegistrationConfirm(String login, String password, String confirmPassword) {
        CheckUserInfoServiceImpl checkUserInfoServiceImpl = new CheckUserInfoServiceImpl();
        boolean validLoginCharacters = checkUserInfoServiceImpl.checkForValidCharacters(login);
        boolean validLoginLength = checkUserInfoServiceImpl.checkLength(login);
        if (!validLoginCharacters || !validLoginLength){
            throw new WrongLoginException();
        }
        boolean validPasswordCharacters = checkUserInfoServiceImpl.checkForValidCharacters(password);
        boolean validPasswordLength = checkUserInfoServiceImpl.checkLength(password);
        boolean arePasswordEquals = checkUserInfoServiceImpl.comparePasswords(password, confirmPassword);
        if(!validPasswordCharacters || !validPasswordLength){
            throw new WrongPasswordExeption();
        }
        if(!arePasswordEquals){
            throw new DifferentPasswordsException();
        }
        return true;
    }

    private boolean checkForValidCharacters(String str) {
        char[] arr = str.toCharArray();
        int counter = -1;
        for (int i = 0; i < arr.length; i++) {
            if (allowedValues.indexOf(arr[i]) < 0) {
                counter++;
            }
        }
        if (counter >= 0) {
            return false;
        }
        return true;
    }


    private boolean checkLength(String str) {
        if (str.length() <= 20) {
            return true;
        }
        return false;
    }

    private boolean comparePasswords(String str, String str2) {
        if (str.equals(str2)) {
            return true;
        }
        return false;
    }
}
