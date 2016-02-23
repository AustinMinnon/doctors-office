import org.junit.*;
import static org.junit.Assert.*;

public class DoctorTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Doctor.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Doctor firstDoctor = new Doctor("John Smith");
    Doctor secondDoctor = new Doctor("John Smith");
    assertTrue(firstDoctor.equals(secondDoctor));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Doctor myDoctor = new Doctor("John Smith");
    myDoctor.save();
    assertTrue(Doctor.all().get(0).equals(myDoctor));
  }

  @Test
  public void find_findDoctorInDatabase_true() {
    Doctor myDoctor = new Doctor("John Smith");
    myDoctor.save();
    Doctor savedDoctor = Doctor.find(myDoctor.getId());
    assertTrue(myDoctor.equals(savedDoctor));
  }
}
