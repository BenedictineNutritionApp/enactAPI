package enactApp.enactAPI.web.controller;

import enactApp.enactAPI.data.model.Symptom;
import enactApp.enactAPI.data.repository.SymptomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class SymptomController {
    @Autowired
    private final SymptomRepository symptomRepository;

    public SymptomController(SymptomRepository symptomRepository) {
        this.symptomRepository = symptomRepository;
    }

    @GetMapping(value = "/api/symptom/all")
    public List<Symptom> getAllSymptom() {
        List<Symptom> symptomList = symptomRepository.findAll();
        return symptomList;
    }

    @PostMapping(path = "/api/symptom/add/")
    public boolean saveSymptom(@RequestBody Symptom symptom){
        Symptom newSymptom = new Symptom();
        newSymptom.setAbdominalPain(symptom.isAbdominalPain());
        newSymptom.setAppetiteLoss(symptom.isAppetiteLoss());
        newSymptom.setBloating(symptom.isBloating());
        newSymptom.setConstipation(symptom.isConstipation());
        newSymptom.setDiarrhea(symptom.isDiarrhea());
        newSymptom.setNausea(symptom.isNausea());
        newSymptom.setStomaProblems(symptom.isStomaProblems());
        newSymptom.setVomiting(symptom.isVomiting());
        newSymptom.setOther(symptom.getOther());
        newSymptom.setDateTime(symptom.getDateTime());
        newSymptom.setUpdated(new Date());
        newSymptom.setCreated(new Date());
        symptomRepository.save(newSymptom);
        return true;
    }

}
