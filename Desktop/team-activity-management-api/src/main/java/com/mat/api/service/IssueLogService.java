package com.mat.api.service;

import com.mat.api.mapper.dto.IssueLogDto;
import org.springframework.data.domain.Page;

public interface IssueLogService {
    IssueLogDto saveIssueLog(IssueLogDto issueLogDto);

    IssueLogDto  getIssueLogById(Long id);
    IssueLogDto  updateIssueLog(Long id, IssueLogDto issueLogDto);


    void deleteIssueLog(Long id);
    Page<IssueLogDto> getPagedListIssueLogDto(Integer page, Integer size);
}


