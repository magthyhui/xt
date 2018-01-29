package gov.hygs.htgl.kqgl.controller;

import gov.hygs.htgl.kqgl.entity.Kqry;
import gov.hygs.htgl.kqgl.service.KqryService;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.provider.Page;

@Component
public class KqryController {
	@Resource
	KqryService kqryService;

	@DataProvider
	public void getKqry(Page page, Map<String, Object> param) {
		kqryService.getKqry(page, param);
	}

	@DataResolver
	@Transactional
	public void updateKqry(List<Kqry> list) {
		kqryService.updateKqry(list);
	}
}
