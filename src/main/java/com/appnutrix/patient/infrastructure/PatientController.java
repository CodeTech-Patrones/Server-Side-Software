package com.appnutrix.patient.infrastructure;

import com.appnutrix.patient.domain.IPatientService;
import com.appnutrix.patient.domain.Patient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
@Api(tags = "Patient", value = "Servicio Web RESTful de Patients")
public class PatientController {

    @Autowired
    private IPatientService patientService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listar Patients", notes = "Método para listar todos los patients")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Patients encontrados"),
            @ApiResponse(code = 404, message = "Patients no encontrados")
    })
    public ResponseEntity<List<Patient>> findAll() {
        try {
            List<Patient> patients = new ArrayList<>();
            patients = patientService.getAll();
            if (patients.isEmpty())
                return new ResponseEntity<List<Patient>>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Patient>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Client por Id", notes = "Método para encontrar un Client por Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Client encontrado"),
            @ApiResponse(code = 404, message = "Client no encontrado")
    })
    public ResponseEntity<Patient> findById(@PathVariable("id") Integer id)
    {
        try{
            Optional<Patient> patient = patientService.getById(id);
            if(!patient.isPresent())
                return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Patient>(patient.get(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Patient>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByUsername/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Client por username", notes = "Método para encontrar un Client por username")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Client encontrado"),
            @ApiResponse(code = 404, message = "Client no encontrado")
    })
    public ResponseEntity<Patient> findByUsername(@PathVariable("username") String username)
    {
        try{
            Patient patient = patientService.findByUsername(username);
            if(patient == null)
                return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Patient>(patient, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Patient>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByFirstName/{firstname}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Clients por firstname", notes = "Método para encontrar Clients por firstname")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Clients encontrados"),
            @ApiResponse(code = 404, message = "Clients no encontrados")
    })
    public ResponseEntity<List<Patient>> findByFirstName(@PathVariable("firstname") String firstname)
    {
        try {
            List<Patient> patients = new ArrayList<>();
            patients = patientService.findByFirstName(firstname);
            if(patients.isEmpty())
                return new ResponseEntity<List<Patient>>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<Patient>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByLastName/{lastname}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Clients por lastname", notes = "Método para encontrar Clients por lastname")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Clients encontrados"),
            @ApiResponse(code = 404, message = "Clients no encontrados")
    })
    public ResponseEntity<List<Patient>> findByLastName(@PathVariable("lastname") String lastname)
    {
        try {
            List<Patient> patients = new ArrayList<>();
            patients = patientService.findByLastName(lastname);
            if(patients.isEmpty())
                return new ResponseEntity<List<Patient>>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<Patient>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByFirstNameAndLastName/{firstname}/{lastname}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Clients por firstname y lastname", notes = "Método para encontrar Clients por firstname y lastname")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Clients encontrados"),
            @ApiResponse(code = 404, message = "Clients no encontrados")
    })
    public ResponseEntity<List<Patient>> findByFirstNameAndLastName(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname)
    {
        try {
            List<Patient> patients = new ArrayList<>();
            patients = patientService.findByFirstNameAndLastName(firstname, lastname);
            if(patients.isEmpty())
                return new ResponseEntity<List<Patient>>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<Patient>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de Clients", notes = "Método para agregar un Client")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Client agregado"),
            @ApiResponse(code = 404, message = "Client no fue agregado")
    })
    public ResponseEntity<Patient> insertPatient(@Valid @RequestBody Patient patient)
    {
        try{
            Patient newPatient = patientService.save(patient);
            return ResponseEntity.status(HttpStatus.CREATED).body(newPatient);
        }catch (Exception e){
            return new ResponseEntity<Patient>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualización de datos de Client", notes = "Método que actualiza los datos de un Client")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Client actualizado"),
            @ApiResponse(code = 404, message = "Client no fue encontrado")
    })
    public ResponseEntity<Patient> updatePatient(
            @PathVariable("id") Integer id, @Valid @RequestBody Patient patient){
        try {
            Optional<Patient> foundPatient = patientService.getById(id);
            if(!foundPatient.isPresent())
                return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
            patient.setId(id);
            patientService.save(patient);
            return new ResponseEntity<Patient>(patient, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Patient>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de un Client", notes = "Método para eliminar un Client")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Client eliminado"),
            @ApiResponse(code = 404, message = "Client no encontrado")
    })
    public ResponseEntity<Patient> deletePatient(@PathVariable("id") Integer id)
    {
        try{
            Optional<Patient> deletedPatient = patientService.getById(id);
            if(!deletedPatient.isPresent())
                return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
            patientService.delete(id);
            return new ResponseEntity<Patient>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Patient>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
