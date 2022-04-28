package prenotazione;

/**
 * Classe che permette di gestire i dati delle prenotazioni.
 */
public class Prenotazione implements Comparable<Object>{
    // ID univoco collegato ad ogni prenotazione.
    private int ID;
    // Nome del cliente che effettua la prenotazione.
    private String nome;
    // Corso a cui si prenota il cliente.
    private String corso;

    /**
     * Costruttore della classe Prenotazione
     * @param _ID id della prenotazione.
     * @param _nome nome del cliente che effettua la prenotazione.
     * @param _corso corso a cui l'utente effettua la prenotazione.
     */
    public Prenotazione (int _ID, String _nome, String _corso) {
        this.ID = _ID;
        this.nome = _nome;
        this.corso = _corso;
    }

    /**
     * Metodo che permette di recuperare l'ID di una prenotazione.
     * @return ID della prenotazione
     */
    public int getID () {
        return this.ID;
    }

    /**
     * Metodo che permette di recuperare il nome del cliente che ha effettuato la prenotazione.
     * @return nome del cliente che ha effettuato la prenotazione.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Metodo che permette di recuperare il corso a cui un cliente si è prenotato.
     * @return corso a cui un cliente si è prenotato.
     */
    public String getCorso() {
        return this.corso;
    }

    /**
     * Metodo che permette di impostare il nome del cliente.
     * @param _nome nome del cliente.
     */
    public void setNome(String _nome) {
        this.nome = _nome;
    }

    /**
     * Metodo che permette di impostare il corso a cui un utente vuole iscriversi.
     * @param _corso corso a cui iscriversi.
     */
    public void setCorso(String _corso) {
        this.corso = _corso;
    }

    /**
     * Metodo che permette di stampare il riepilogo di una prenotazione.
     * @return riepilogo di una prenotazione.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("numero prenotazione: ");
        sb.append(ID);
        sb.append("\n");
        sb.append("nome: ");
        sb.append(nome);
        sb.append("\n");
        sb.append("corso: ");
        sb.append(corso);
        sb.append("\n");

        return sb.toString();
    }

    /**
     * Metodo che permette di capire se due oggetti Prenotazione sono uguali.
     * @param obj Oggetto da comparare.
     * @return True se sono uguali, False altrimenti.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Prenotazione other = (Prenotazione) obj;
        if (ID < 0) {
            return other.getID() >= 0;
        } else return ID == other.getID();
    }

    /**
     * Implementazione del metodo compareTo.
     * @param obj l'oggetto che dev'essere comparato.
     * @return se il return è 0, l'oggetto è il medesimo.
     */
    public int compareTo(Object obj) {
        return(this.ID - ((Prenotazione) obj).ID);
    }
}
