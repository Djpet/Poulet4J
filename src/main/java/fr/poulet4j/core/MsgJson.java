package fr.poulet4j.core;

public class MsgJson {

    private String type;
    private String json;

    public MsgJson(String type, String json) {
        this.type = type;
        this.json = json;
    }
    
    /**
     * Get the type.
     * 
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the type.
     * 
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get the json.
     * 
     * @return the json
     */
    public String getJson() {
        return json;
    }

    /**
     * Set the json.
     * 
     * @param json the json to set
     */
    public void setJson(String json) {
        this.json = json;
    }
}
