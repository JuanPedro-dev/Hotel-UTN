import java.util.ArrayList;

public class AdminManager {
    private ArrayList<Admin> adminList;

    public AdminManager(ArrayList<Admin> adminList) {
        this.adminList = adminList;
    }

    public void addAdmin(Admin admin) {
        adminList.add(admin);
    }

    public void removeAdmin(Admin admin) {
        adminList.remove(admin);
    }

    public ArrayList<Admin> getAdminList() {
        return adminList;
    }
}
