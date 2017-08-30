import java.sql.ResultSet;

public class Proposal {
    String proposalid;
    String proposalname;
    String proposalwriter;
    String deadline;
    String content;

    public void setProposalid(String proposalid){
        this.proposalid=proposalid;
    }
    public void setProposalname(String proposalname){
        this.proposalname=proposalname;
    }
    public void setProposalwriter(String proposalwriter){
        this.proposalwriter=proposalwriter;
    }
    public void setDeadline(String deadline){
        this.deadline=deadline;
    }
    public void setContent(String content){
        this.content=content;
    }

    public String getProposalid(){
        return proposalid;
    }
    public String getProposalname(){
        return  proposalname;
    }
    public String getProposalwriter(){
        return proposalwriter;
    }
    public String getDeadline(){
        return deadline;
    }
    public String getContent(){
        return content;
    }



}
