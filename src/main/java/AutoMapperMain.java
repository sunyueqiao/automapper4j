import com.github.entity.UserInfo;
import com.github.entity.UserInfoDto;
import com.github.sunyueqiao.AutoMapper;

public class AutoMapperMain {
    public static void main(String[] args){
        UserInfo userInfo=new UserInfo();
        userInfo.setUserId(123);
        userInfo.setUserName("张三");
        UserInfoDto userInfoDto = (UserInfoDto) AutoMapper.initilize(userInfo,new UserInfoDto()).mapping();
        System.out.println(userInfoDto.getUserName());
    }
}
