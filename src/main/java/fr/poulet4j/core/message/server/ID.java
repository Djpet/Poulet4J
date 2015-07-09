package fr.poulet4j.core.message.server;

public class ID extends GameServerMessage {

    public static final String TYPE = "id";

    private long id;

    public ID() {
        super(TYPE);
    }

    /**
     * Get the id.
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Set the id.
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ID [id=" + id + "]";
    }
}
