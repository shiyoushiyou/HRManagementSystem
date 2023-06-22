package jp.co.hrms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.hrms.mapper.LoginMapper;
import jp.co.hrms.model.User;

@Service
public class LoginService {

	@Autowired
	private LoginMapper mapper;

	public String loginCheck(User user) {
		String msg = null;
		User userDB = mapper.getUserByUserid(user.getLoginId());
		System.out.println(userDB);
		if (userDB != null) {
			String password = user.getPassword();
			
			// 将user的positionId属性设置为userDB的positionId属性
            user.setPositionId(userDB.getPositionId());
            // 将user的loginId属性设置为userDB的loginId属性
            user.setLoginId(userDB.getId());

			// ◆1レコード 取得できる場合
			if (!password.equals(userDB.getPassword())) {
				// ◆DB.パスワード＜＞画面.パスワード(MD5で暗号化した内容)
				// PSW認証エラー
				msg = "パスワードが間違っています。";
			  } else if ("離職".equals(userDB.getStatus())) {
	                // 离职状态
	                msg = "退職した社員はログインできません。";
			} else {
				msg = "success";
			}
		} else {
			// ◆0レコードの場合
			msg = "該当ユーザーが存在しません。";
		}
		System.out.println(msg);
		return msg;
	}
}

