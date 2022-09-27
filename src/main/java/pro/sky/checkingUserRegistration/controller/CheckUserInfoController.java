package pro.sky.checkingUserRegistration.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.checkingUserRegistration.exceptions.DifferentPasswordsException;
import pro.sky.checkingUserRegistration.exceptions.WrongLoginException;
import pro.sky.checkingUserRegistration.exceptions.WrongPasswordExeption;
import pro.sky.checkingUserRegistration.service.CheckUserInfoService;
import pro.sky.checkingUserRegistration.service.CheckUserInfoServiceImpl;

@RestController
public class CheckUserInfoController {

    private final CheckUserInfoService checkUserInfoService;

    public CheckUserInfoController(CheckUserInfoService checkUserInfoService) {
         this.checkUserInfoService = checkUserInfoService;
    }

    //registration?login=java_skypro&password=D_1hWiKjjP_9&confirmPassword=D_1hWiKjjP_9
    @GetMapping()
    public String showWelcome() {
        return "Welcome!";
    }

    @GetMapping(path = "/registration")
    public static boolean confirmRegistration(@RequestParam String login, @RequestParam String password,
                                      @RequestParam String confirmPassword){
        CheckUserInfoServiceImpl checkUserInfoServiceImpl = new CheckUserInfoServiceImpl();
        boolean res;
        try {
            res = checkUserInfoServiceImpl.isRegistrationConfirm(login, password, confirmPassword);
        } catch (WrongLoginException e){
            System.out.println("Wrong login");
            return false;
        } catch (WrongPasswordExeption e) {
            System.out.println("Wrong password");
            return false;
        }catch (DifferentPasswordsException e){
            System.out.println("Passwords are different");
            return false;
        }
        return res;
    }
}
