package motive.e_sportstreak.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date difference, as minutes (abs)
 */

public class TimeDiff {

  static SimpleDateFormat df=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

  private String sD1;
  private String sD2;

  public TimeDiff(String sD1, String sD2) {
    this.sD1=sD1;
    this.sD2=sD2;
  }
  /**
   * Parse date strings in format like: 2005-01-01 22:10:35
   */
  public long getDifferenceInMinutes () throws ParseException {
    Date d1=df.parse(sD1);
    Date d2=df.parse(sD2);
    long d1Ms=d1.getTime();
    long d2Ms=d2.getTime();
    return Math.abs((d1Ms-d2Ms)/60000);
  }
  /**
   ***************** MAIN *******************
   * @param args
   */
}
