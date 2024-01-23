package com.solux.pyi.pyiplanyouridea.config.auth;

import com.solux.pyi.pyiplanyouridea.users.domain.Users;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class PrincipalDetails implements UserDetails {
//public class PrincipalDetails implements UserDetails, OAuth2User {

// PrincipalDetails가 UserDetails와 OAuth2User를 상속받게 해서
// 어느 쪽 타입을 받든 Authentication 객체 안에 들어갈 수 있도록 한다.
// PrincipalDetails 클래스를 만든 목적은 두 가지이다.
// 1. 시큐리티 세션에 들어갈 수 있는 타입은 Authentication 객체이다.
// Authentication 객체에 들어갈 수 있는 두 필드는 OAuth2User 타입과 UserDetails 타입이다.
// 회원가입을 하게 되면 User 오브젝트가 필요하다.
// 그런데 OAuth2User와 UserDetails는 User 오브젝트를 포함하고 있지 않다.
// 그래서 PrincipalDetails 클래스를 만들어 UserDetails를 implementation하여(상속받아서) User 오브젝트를 가질 수 있게 하였다.
// 그래서 Authentication에 UserDetails 대신 PrincipalDetails를 넣을 수 있게 한 것이다.
// 이렇게 하면 PrincipalDetails는 User 오브젝트를 가지고 있으므로 세션 정보에 접근할 수 있게 된다.
// 그런데 OAuth로 로그인할 때는 OAuth2User를 사용해야 하므로 프로그램이 굉장히 복잡해지는 문제가 되었다.
// 그래서 OAuth2User도 PrincipalDetails가 가질 수 있게 한 것이다.
// OAuth2User도 PrincipalDetails에 묶이면서 User 오브젝트를 가지게 되었다.
// 따라서 User 오브젝트를 가지고 있는 PrincipalDetails만 꺼내 쓰면 되는 상황이 되어 문제가 해결되었다.

    private Users users;
    // 콤포지션
    //private Map<String, Object> attributes;

    // 일반 로그인 시 사용하는 생성자
    public PrincipalDetails(Users users) {
        this.users = users;
    }
//    // OAuth 로그인 시 사용하는 생성자
//    // OAuth2User로 로그인 할 때 Authentication에 PrincipalDetails를 저장할 것이다.
//    // OAuth 로그인을 하면 attributes 정보와 user 정보를 가지게 될 것이다.
//    // attributes 정보를 토대로 user 정보가 생성될 것이다.
//    public PrincipalDetails(Users users, Map<String, Object> attributes) {
//        this.users = users;
//        this.attributes = attributes;
//    }

    // 해당 User의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        // 본래 유저의 권한은 user.Role()이지만
        // user.Role()은 String을 리턴하므로
        // 이를 리턴할 순 없다.
        // 때문에 함수의 타입으로 리턴시켜줘야한다.
        Collection<GrantedAuthority> collect = new ArrayList<>();
        // ArrayList는 Collection의 자식이므로 이를 리턴해주면 된다.

//        collect.add(new GrantedAuthority() {
//            // 여기 안에서는 String을 리턴해줄 수 있다.
//            @Override
//            public String getAuthority() {
//                return users.getUserRole();
//            }
//        });

        collect.add(() -> {
            return users.getUserRole().name();
        });

        // collect 안에 GrantedAuthority 타입을 add해줘야 한다.
        return collect;
        // collect 안에 user.getRole()이 추가됐으므로
        // collect를 리턴해주면 된다.
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    // 계정의 고유한 값을 PK로 넘겨준다
    @Override
    public String getUsername() {
        return users.getUserId();
    }

    // 계정이 만료되지 않았는지
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠기지 않았는지
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 계정의 비밀번호 변경이 1년이 지나지 않았는지
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 활성화 되었는지
    @Override
    public boolean isEnabled() {

        // 사이트에서 1년 동안 회원이 로그인하지 않으면
        // 휴면 계정으로 전환하기로 한 경우
        //user.getLoginDate();
        // 위를 체크하여
        // 현재시간 - 마지막으로 로그인한 시간
        // => 1년을 초과하면 return false; 해줄 수 있다.

        return true;
    }

//    @Override
//    public Map<String, Object> getAttributes() {
//        return attributes;
//    }
//
//    @Override
//    public String getName() {
//        //return attributes.get("sub");
//        // 구글 회원 정보 PK가 sub이다.
//        return null;
//    }

}
