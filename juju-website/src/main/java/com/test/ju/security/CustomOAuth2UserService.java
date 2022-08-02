package com.test.ju.security;

import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.test.ju.domain.entity.Member;
import com.test.ju.domain.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;//JujuProjectApplication
	
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User=super.loadUser(userRequest);
		//소셜로그인 인증완료상태//인증옵션
		String registrationId=userRequest.getClientRegistration().getRegistrationId();
		System.out.println("registrationId : "+registrationId);
		//소셜정보를 memberDB에 저장하는 경우
		//DB저장 //엔티티에 member social 저장//기존거랑 db 병합
		return saveSocialUser(oAuth2User, registrationId);
	}
	
	
	private OAuth2User saveSocialUser(OAuth2User oAuth2User, String registrationId) {
		
		Map<String, Object> userInfo= oAuth2User.getAttributes();
		
		for(String key: userInfo.keySet()) {
			System.out.println(key+":"+userInfo.get(key));//attributes안의 value정보
		}
		String name=null;
		String email=null;
		String pass=null;
		
		if(registrationId.equals("google")) {//이름패턴이 다 다름. 구글이어서 구글로 넣어줘서 구분함.
			name=oAuth2User.getAttribute("name");
			email=oAuth2User.getAttribute("email");//소셜은 비밀번호 안받아와도 된다?
			pass=registrationId;
			
			
//			Map<String, Object> userInfo= oAuth2User.getAttributes();
//			for(String key: userInfo.keySet()) {
//				System.out.println(key+":"+userInfo.get(key));//attributes안의 value정보
//			}
			
			//위랑 아래랑 같은 거
//			userInfo.keySet().forEach(key->{
//				System.out.println(key+":");
//				System.out.println(userInfo.get(key));//attributes안의 value정보
//				
//			});;
			
		}else if(registrationId.equals("naver")) {//response에 정보가 들어가있음 네이버는! 제이슨?
			Map<String, Object > response=oAuth2User.getAttribute("response");
			name=(String)response.get("name");
			email=(String)response.get("email");//소셜은 비밀번호 안받아와도 된다?
			pass=registrationId;
			
//			for(String key: response.keySet()) {
//				System.out.println(key+":"+response.get(key));
//			}
		}else if(registrationId.equals("kakao")) {
			Map<String,Object> kakaoAccount = oAuth2User.getAttribute("kakao_account");
			email =(String) kakaoAccount.get("email");
			@SuppressWarnings("unchecked")
			Map<String,Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
			name = (String)profile.get("nickname");
			pass= registrationId;
		}
		
		Optional<Member> result = memberRepository.findByEmail(email);
//				.map(e->e.socialUpdate(name,passwordEncoder.encode(pass)))
//				.map(CustomUserDetails::new); //일반회원 또는 소셜회원 존재하는지 체크  
			
		if( result.isPresent()) {
			Member entity  = memberRepository.save(
					result.get().socialUpdate(name, passwordEncoder.encode(pass)
							)); //업데이트하기 위해서 
			return new CustomUserDetails(entity);
		}

		
		//가입이 되지 않은 회원은 소셜정보로 회원 가입
		
		Member entity=memberRepository.save(Member.builder()
				.email(email).name(name).pass(passwordEncoder.encode(pass))//일반회원
				.userIp("127.0.0.1")
				.isSocial(true)//소셜
				.build().addRole(MemberRole.USER));//권한
		
		return new CustomUserDetails(entity);//이게 머노
		
	}
	
	


}
