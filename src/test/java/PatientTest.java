import org.junit.*;
import static org.junit.Assert.*;

public class PatientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Patient.all().size());
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAreTheSame() {
    Patient firstPatient = new Patient("John Patient");
    Patient secondPatient = new Patient("John Patient");
    assertTrue(firstPatient.equals(secondPatient));
  }

  @Test
  public void save_returnsTrueIfDescriptionsAretheSame() {
    Patient myPatient = new Patient("John Patient");
    myPatient.save();
    assertTrue(Patient.all().get(0).equals(myPatient));
  }

  @Test
  public void save_assignsIdToObject() {
    Patient myPatient = new Patient ("John Patient");
    myPatient.save();
    Patient savedPatient = Patient.all().get(0);
    assertEquals(myPatient.getId(), savedPatient.getId());
  }

  @Test
  public void find_findsPatientInDatabase_true() {
    Patient myPatient = new Patient ("John Patient");
    myPatient.save();
    Patient savedPatient = Patient.find(myPatient.getId());
    assertTrue(myPatient.equals(savedPatient));
  }
}
