package com.global.school.service;

import com.global.school.entity.Result;
import com.global.school.repository.ResultRepository;
import org.hibernate.query.internal.ResultSetMappingResolutionContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
    private ResultRepository resultRepository;

    public Result createResult(Result result){
        return resultRepository.save(result);
    }
    public Result getByResultId(Long resultId){
        return resultRepository.findById(resultId).orElseThrow(()->new RuntimeException("Result not found. "));
    }
    public List<Result> getAllResults(){
        return resultRepository.findAll();
    }
    public void deleteResult(Long resultId){
        resultRepository.deleteById(resultId);
    }
    public Result updateResult(Long resultId, Result result){
        Result existingResult=getByResultId(resultId);
        existingResult.setScore(result.getScore());
        existingResult.setExam(result.getExam());
        existingResult.setAssignment(result.getAssignment());
        existingResult.setStudent(result.getStudent());
        return resultRepository.save(existingResult);
    }
}
