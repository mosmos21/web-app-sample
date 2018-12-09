package jp.co.unirita.webapp.sample.service;

import jp.co.unirita.webapp.sample.domain.master.tag.TagMaster;
import jp.co.unirita.webapp.sample.domain.master.tag.TagMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    TagMasterRepository tagMasterRepository;

    public List<TagMaster> getAllTags() {
         return tagMasterRepository.findAll();
    }
}
