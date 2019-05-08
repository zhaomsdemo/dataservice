package com.zjh.dataservice.controller.jpa;

import com.zjh.dataservice.entity.jpa.Education;
import com.zjh.dataservice.entity.jpa.Talent;
import com.zjh.dataservice.exception.DataNotFoundException;
import com.zjh.dataservice.repository.jpa.EducationRepository;
import com.zjh.dataservice.repository.jpa.TalentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/talent")
public class TalentController {

    @Autowired
    private TalentRepository talentRepository;
    @Autowired
    private EducationRepository educationRepository;

    @PostMapping("/{talentId}/educations")
    public ResponseEntity<Education> postEducation(@PathVariable String talentId, @RequestBody Education education)
            throws DataNotFoundException {
        Talent talent = talentRepository.findById(talentId).orElseThrow(DataNotFoundException::new);
        education.setId(UUID.randomUUID().toString());
        education.setTalent(talent);
        Education response = educationRepository.save(education);

        return new ResponseEntity<Education>(response, HttpStatus.OK);
    }
}
