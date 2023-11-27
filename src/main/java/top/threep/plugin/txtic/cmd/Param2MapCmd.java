package top.threep.plugin.txtic.cmd;

public class Param2MapCmd implements Cmd {
    private final String options;

    public Param2MapCmd(String options) {
        this.options = options;
    }


    @Override
    public String run(String text) {
        String[] params = text.split(",");
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if ("n".equals(this.options)) {
            sb.append("\n");
        }
        for (String param : params) {
            param = param.split("=")[0].split(":")[0].trim();
            sb.append("'").append(param).append("': ").append(param).append(", ");
            if ("n".equals(this.options)) {
                sb.append("\n");
            }
        }
        sb.delete(sb.length() - 2, sb.length());
        if ("n".equals(this.options)) {
            sb.append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
