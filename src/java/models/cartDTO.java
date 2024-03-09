package models;

import java.util.ArrayList;
import java.util.List;

public class cartDTO {
    private userDTO user;
    private List<bookCartDTO> list;

    public cartDTO(userDTO user) {
        this.user = user;
        list = new ArrayList<>();
    }  

    public cartDTO(userDTO user, List<bookCartDTO> list) {
        this.user = user;
        this.list = list;
    }

    public userDTO getUser() {
        return user;
    }

    public void setUser(userDTO user) {
        this.user = user;
    }

    public List<bookCartDTO> getList() {
        return list;
    }

    public void setList(List<bookCartDTO> list) {
        this.list = list;
    }
    
    
}
