import javax.swing.*;
import java.sql.*;

public class RecommendPerson {
    String recommendName;
    String zhicheng;
    String workplace;
    String zhiwu;
    String phoneNumber;
    String email;
    String beRecommend;
    String reason;

    public void setRecommendName(String recommendName){
        this.recommendName=recommendName;
    }
    public void setZhicheng(String zhicheng){
        this.zhicheng=zhicheng;
    }
    public void setWorkplace(String workplace){
        this.workplace=workplace;
    }
    public void setZhiwu(String zhiwu){
        this.zhiwu=zhiwu;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setBeRecommend(String beRecommend){
        this.beRecommend=beRecommend;
    }
    public void setReason(String reason){
        this.reason=reason;
    }
    public String getRecommendName(){
        return recommendName;
    }
    public String getZhicheng(){
        return zhicheng;
    }
    public String getWorkplace(){
        return workplace;
    }
    public String getZhiwu(){
        return zhiwu;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getEmail(){
        return email;
    }
    public String getBeRecommend(){
        return beRecommend;
    }
    public String getReason(){
        return reason;
    }

    public void setRecommendPerson(String name){
        RecommendControl recommendControl=new RecommendControl();
        recommendControl.getConnection();

    }
}
