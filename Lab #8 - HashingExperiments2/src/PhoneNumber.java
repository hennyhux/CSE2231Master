
public class PhoneNumber {

    /**
     * The phone number representation.
     */
    private String rep;

    public PhoneNumber(String pNum) {
        this.rep = pNum;
    }

    @Override
    public int hashCode() {
        int sum = 0;
        for (int i = 0; i < this.rep.length(); i++) {
            if (!(this.rep.charAt(i) == '-')) {
                sum += Character.digit(this.rep.charAt(i), 30);
            }

            // if (this.rep.charAt(i) is a character){
            //find the value of the character not AScii but assigned int val
            //}

        }
        return sum;
    }

}
