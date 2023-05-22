package tfg.front.domain;

public class Terminal {
    int idterminal;
    String name;
    String ipv4;
    String ipv6;

    public Terminal() {
    }

    public int getIdterminal() {
        return idterminal;
    }

    public void setIdterminal(int idterminal) {
        this.idterminal = idterminal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public String getIpv6() {
        return ipv6;
    }

    public void setIpv6(String ipv6) {
        this.ipv6 = ipv6;
    }

    @Override
    public String toString() {
        return "Terminal{" +
                "idterminal=" + idterminal +
                ", name='" + name + '\'' +
                ", ipv4='" + ipv4 + '\'' +
                ", ipv6='" + ipv6 + '\'' +
                '}';
    }
}
