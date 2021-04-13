package enactApp.enactAPI.web.controller;

import enactApp.enactAPI.data.model.*;
import enactApp.enactAPI.data.repository.*;
import enactApp.enactAPI.data.translator.FoodTranslator;
import enactApp.enactAPI.web.models.FoodView;
import enactApp.enactAPI.data.model.FitnessActivity;
import enactApp.enactAPI.data.model.Symptom;
import enactApp.enactAPI.data.repository.SymptomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import enactApp.enactAPI.data.model.ActivityOption;
import enactApp.enactAPI.data.repository.ActivityOptionRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/symptom")
public class SymptomController {


    @Autowired
    private SymptomRepository symptomRepository;


    @GetMapping(value = "/all")
    public List<Symptom> getAllSymptom() {
        List<Symptom> symptomList = symptomRepository.findAll();
        Collections.sort(symptomList);
        return symptomList;
    }
    @GetMapping(value = "/all/user")
    public List<Symptom> getAllSymptomByUserId(@RequestParam String userId) {
        List<Symptom> symptomListList = symptomRepository.findAllByUserId(Integer.parseInt(userId));
        Collections.sort(symptomListList);
        return symptomListList;
    }

    @PostMapping(path = "/add/")
    public boolean saveSymptom(@RequestBody Symptom symptom){
        Symptom newSymptom = new Symptom();
        newSymptom.setUserId(symptom.getUserId());
        newSymptom.setAbdominalPain(symptom.isAbdominalPain());
        newSymptom.setAppetiteLoss(symptom.isAppetiteLoss());
        newSymptom.setBloating(symptom.isBloating());
        newSymptom.setConstipation(symptom.isConstipation());
        newSymptom.setDiarrhea(symptom.isDiarrhea());
        newSymptom.setNausea(symptom.isNausea());
        newSymptom.setStomaProblems(symptom.isStomaProblems());
        newSymptom.setVomiting(symptom.isVomiting());
        newSymptom.setOther(symptom.getOther());
        newSymptom.setId(symptom.getId());
        newSymptom.setDateTime(symptom.getDateTime());
        newSymptom.setUpdated(new Date());
        newSymptom.setCreated(new Date());
        symptomRepository.save(newSymptom);
        return true;
    }

    @PutMapping(path = "/update")
    public boolean updateSymptom(@RequestBody Symptom symptom){
        Symptom oldSymptom = symptomRepository.getOne(symptom.getId());
        oldSymptom.setAbdominalPain(symptom.isAbdominalPain());
        oldSymptom.setAppetiteLoss(symptom.isAppetiteLoss());
        oldSymptom.setBloating(symptom.isBloating());
        oldSymptom.setConstipation(symptom.isConstipation());
        oldSymptom.setDiarrhea(symptom.isDiarrhea());
        oldSymptom.setNausea(symptom.isNausea());
        oldSymptom.setStomaProblems(symptom.isStomaProblems());
        oldSymptom.setVomiting(symptom.isVomiting());
        oldSymptom.setOther(symptom.getOther());
        oldSymptom.setId(symptom.getId());
        oldSymptom.setDateTime(symptom.getDateTime());
        oldSymptom.setUpdated(new Date());
        symptomRepository.save(oldSymptom);
        return true;
    }


}
