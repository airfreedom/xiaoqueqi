public class RegistrationPerson {
    String pid;
    String pname;
    String pgender;
    String pbirthday;
    String paddress;
    String pphoneNumber;
    String precommendPerson;
    String phangyefenhui;
    String pzhuanweihui;
    String pworkplace;
    String pjob;
    String pemail;
    String pmember;
    /*public Person (){
        pid=pname=pgender=pbirthday=paddress=pphoneNumber=precommendPerson=phangyefenhui=null;
        pzhuanweihui=pworkplace=pjob=pemail=pmember=null;

    }*/
    public void setName(String name){
        pname=name;
    }
    public void setGender(String gender){
        pgender=gender;
    }
    public void setBirthday(String birthday){
        pbirthday=birthday;
    }
    public void setAddress(String address){
        paddress=address;
    }
    public void setPhoneNumber(String phoneNumber){
        pphoneNumber=phoneNumber;
    }
    public void setRecommendPerson(String recommandPerson){
        precommendPerson=recommandPerson;
    }
    public void setHangyefenhui(String hangyefenhui){
        phangyefenhui=hangyefenhui;
    }
    public void setZhuanweihui(String zhuanweihui){
        pzhuanweihui=zhuanweihui;
    }
    public String getPname(){
        return pname;
    }
    public String getPid(){
        return pid;
    }
    public String getPgender(){
        return pgender;
    }
    public String getPbirthday(){
        return pbirthday;
    }
    public String getPaddress(){
        return paddress;
    }
    public String getPphoneNumber(){
        return pphoneNumber;
    }
    public String getPrecommendPerson(){
        return precommendPerson;
    }
    public String getPhangyefenhui(){
        return phangyefenhui;
    }
    public String getPzhuanweihui(){
        return pzhuanweihui;
    }
    public void print(){
        System.out.print(pname+" "+pbirthday+paddress+pgender);
    }
}
