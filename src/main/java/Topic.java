public class Topic {

    private String name;
    private String path;
    private int ct;

    public Topic() {
        name = "";
        path = null;
        ct = 40;
    }

    public Topic(String format) {
        String[] small = format.split(";");
        this.ct = Integer.parseInt(small[1].substring(small[1].indexOf('=') + 1));
        this.path = small[0].replace('<', ' ').replace('>', ' ').trim();
        this.name = path.substring(this.path.lastIndexOf('/') + 1);
    }

    public Topic(String name, String path, int ct) {
        this.name = name;
        this.path = path;
        this.ct = ct;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public int getCt() {
        return ct;
    }
}
