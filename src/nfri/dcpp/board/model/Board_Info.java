package nfri.dcpp.board.model;

import java.util.Vector;

public class Board_Info {
	private int num; 
    private String writer;
    private String subject;
    private String email;
    private String content;
    private String passwd;
    private String reg_date;
    private int readcount;
    private String ip;
    private int ref;
    private int re_step;	
    private int re_level;

	public void setNum(int num){
    	this.num=num;
    }
    public void setWriter (String writer) {
        this.writer = writer;
    }
    public void setSubject (String subject) {
        this.subject = subject;
    }
    public void setEmail (String email) {
        this.email = email;
    }
    public void setContent (String content) {
        this.content = content;
    }
    public void setPasswd (String passwd) {
        this.passwd = passwd;
    }
    public void setReg_date (String reg_date) {
        this.reg_date = reg_date;
    }
	public void setReadcount(int readcount){
	  	this.readcount=readcount;
	}
    public void setIp (String ip) {
        this.ip = ip;
    }
	public void setRef (int ref) {
        this.ref = ref;
    }
	public void setRe_level (int re_level) {
        this.re_level=re_level;
    }
	public void setRe_step (int re_step) {
        this.re_step=re_step;
    }
    
    public int getNum(){
    	return num;
    }
    public int getReadcount(){
   	    return readcount;
    }
    public String getWriter () {
        return writer;
    }
    public String getSubject () {
        return subject;
    }
    public String getEmail () {
        return email;
    }
    public String getContent () {
        return content;
    }
    public String getPasswd () {
        return passwd;
    }
    public String getReg_date () {
        return reg_date;
    }
    public String getIp () {
        return ip;
    }
    public int getRef () {
        return ref;
    }
	public int getRe_level () {
        return re_level;
    }
	public int getRe_step () {
        return re_step;
    }
	
	public Vector<Comparable> getDataList(){
		Vector<Comparable> value = new Vector<Comparable>();
		value.addElement(this.num);
		value.addElement(this.writer);
		value.addElement(this.email);
		value.addElement(this.subject);
		value.addElement(this.passwd);
		value.addElement(this.reg_date);
		value.addElement(this.ref);
		value.addElement(this.re_step);
		value.addElement(this.re_level);
		value.addElement(this.content);
		value.addElement(this.ip);
		return value;
	}
}
	