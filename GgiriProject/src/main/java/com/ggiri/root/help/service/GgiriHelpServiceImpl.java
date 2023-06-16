package com.ggiri.root.help.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ggiri.root.help.dto.GgiriHelpDTO;
import com.ggiri.root.mybatis.help.GgiriHelpMapper;

@Service
public class GgiriHelpServiceImpl implements GgiriHelpService{
	
	@Autowired
	private GgiriHelpMapper ghm;
	
	
	
	
	@Override
	public void HelpList(Model model) {
		model.addAttribute("helpList", ghm.HelpList());
	}

	







	@Override
	public int helpWrite(GgiriHelpDTO dto) {
		try {
			return ghm.helpWrite(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}









	@Override
	public void helpView(int helpNo, Model model) {
		// TODO Auto-generated method stub
		model.addAttribute("data", ghm.helpView(helpNo));
	}









	@Override
	public void modify(GgiriHelpDTO dto) {
		ghm.modify(dto);
		
	}









	@Override
	public void helpDelete(int helpNo) {
		ghm.helpDelete(helpNo);
	}
	
	
	
	
}