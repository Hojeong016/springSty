package org.cos.security1.auth;


import org.cos.security1.Repository.UserRepository;
import org.cos.security1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


//시큐리티 설정에서   .loginProcessingUrl("/login");
// 위에서 login 요청이 오면 자동으로 UserDetailsService 타입으로 ioc 되어있는 loadUserByUsername 변수가 실행이 되는 규칙을 가지고 있다.
/*@Service
public class PrincipalDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    public PrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //어디로 리턴될까? 바로
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username"+username);
        //중요 : username 의 의미 -> html 인풋 태그와 관련 이름 통일
        User userEntity = userRepository.findByUsername(username);
        if(userEntity != null){
            return new PrincipalDetails(userEntity);
        }
        return null;
    }
}*/

@Service
public class PrincipalDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            return null;
        }else {
            return new PrincipalDetails(user);
        }

    }

}