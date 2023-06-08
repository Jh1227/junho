
package com.ggiri.root.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ggiri.root.project.VO.LikeVO;
import com.ggiri.root.project.dto.ProjectDTO;
import com.ggiri.root.project.service.ProjectService;
import com.ggiri.root.project.service.ProjectServiceImpl;

@Controller
@RequestMapping("ggiriProject")
public class ProjectController {

	@Autowired
	private ProjectService ps;
	
	@Autowired
	private ProjectServiceImpl projectService;

    @RequestMapping("projectWrite")
    public String proWrite() {
        return "ggiriProject/projectWrite";
    }

    @GetMapping("projectView")
    public String projectView(@RequestParam("projectNum") int projectNum, Model model, HttpSession session) {
        ps.projectView(projectNum, model);

//        // likeId 값 설정
//        String id = (String) session.getAttribute("loginUser");
//        int likeId = projectService.getLikeIdByUser(projectNum, id);
//        model.addAttribute("likeId", likeId);

        return "ggiriProject/projectView";
    }

    @PostMapping("projectSave")
    public String projectSave(ProjectDTO dto) {
        ps.insertPro(dto);
        return "redirect:/ggiriProject/projectList";
    }

    @GetMapping("modifyForm")
    public String modifyForm(@RequestParam("projectNum") int projectNum, Model model) {
        ps.projectView(projectNum, model);
        return "ggiriProject/modifyForm";
    }

    @PostMapping("modify")
    public String modify(ProjectDTO dto) {
        ps.modify(dto);
        return "redirect:/ggiriProject/projectList";
    }

    @GetMapping("delete")
    public String delete(@RequestParam("projectNum") int projectNum) {
        ps.delete(projectNum);
        return "redirect:/ggiriProject/projectList";
    }

    @GetMapping("projectList")
    public String projectList(
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(value = "keyword", required = false) String keyword,
        @RequestParam(value = "condition", defaultValue = "title") String condition,
        Model model
    ) {
        int perPage = 10; // 한 페이지에 보여줄 프로젝트 개수

        if (keyword != null && !keyword.isEmpty()) {
            // 검색어가 입력된 경우 검색 기능 적용
            int totalCount = ps.getProjectCountBySearch(keyword, condition);
            int totalPages = (int) Math.ceil((double) totalCount / perPage);
            int startRow = (page - 1) * perPage + 1;
            int endRow = startRow + perPage - 1;
            List<ProjectDTO> projectList = ps.getProjectListBySearch(keyword, condition, startRow, endRow);
            
            model.addAttribute("keyword", keyword);
            model.addAttribute("condition", condition);
            model.addAttribute("projectList", projectList);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);
        } else {
            // 검색어가 없는 경우 전체 프로젝트 목록 조회
            List<ProjectDTO> projectList = ps.getProjectList(page, perPage);
            int totalCount = ps.getProjectCount();
            int totalPages = (int) Math.ceil((double) totalCount / perPage);
            
            model.addAttribute("projectList", projectList);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);
        }

        return "ggiriProject/projectList";
    }
    
    @PostMapping("/project/increase-like-count/{projectNum}")
    @ResponseBody
    public ResponseEntity<String> increaseLikeCount(@PathVariable("projectNum") int projectNum, HttpSession session) {
        String id = (String) session.getAttribute("id");
        try {
            projectService.increaseLikeCountByUser(projectNum, id);
            return ResponseEntity.ok("좋아요 개수가 증가되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("좋아요 개수 증가에 실패했습니다.");
        }
    }



    
//    @GetMapping("/projectList")
//    public String projectList(Model model) {
//        projectService.projectList(model);
//        return "project/projectList";
//    }

//    @GetMapping("/projectView/{projectNum}")
//    public String projectView(@PathVariable("projectNum") int projectNum, Model model) {
//        projectService.projectView(projectNum, model);
//        return "project/projectView";
//    }
//
//    @PostMapping("/increase-like-count/{projectNum}")
//    @ResponseBody
//    public String increaseLikeCount(@PathVariable("projectNum") int projectNum, HttpSession session) {
//        String id = (String) session.getAttribute("id");
//        projectService.increaseLikeCountByUser(projectNum, id);
//        return "success";
//    }
//    
}