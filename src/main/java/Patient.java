import java.util.*;
import org.sql2o.*;

public class Patient {
  private int id;
  private String name;
  private int doctor_id;
  private String birth_date;

  public Patient(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getDescription() {
    return name;
  }

  public static List<Patient> all() {
    String sql = "SELECT id, name FROM patients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Patient.class);
    }
  }

  @Override
  public boolean equals(Object otherPatient){
    if (!(otherPatient instanceof Patient)) {
      return false;
    } else {
      Patient newPatient = (Patient) otherPatient;
      return this.getDescription().equals(newPatient.getDescription()) &&
             this.getId() == newPatient.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO Patients (name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  public static Patient find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM patients WHERE id = :id";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Patient.class);
    }
  }
}
