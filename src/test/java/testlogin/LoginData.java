package testlogin;

public class LoginData {
        private String Email;
        private String password;
        private int TenantId;

    public LoginData(String Email, String password, int TenantId) {
        this.Email = Email;
        this.password = password;
        this.TenantId = TenantId;
    }


        // Getters and Setters
        public String getEmail() { return Email; }
        public void setEmail(String email) { this.Email = Email; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }

        public int getTenantId() { return TenantId; }
        public void setTenantId(int TenantId) { this.TenantId = TenantId; }
    }

