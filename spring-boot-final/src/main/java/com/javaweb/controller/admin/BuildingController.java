package com.javaweb.controller.admin;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.enums.District;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.BuildingService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController(value = "buildingControllerOfAdmin")
@Transactional
public class BuildingController {
    @Autowired
    private IUserService userService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private BuildingRepository buildingRepository;

    @RequestMapping(value = "/admin/building-list", method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute BuildingSearchRequest buildingSearchRequest, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/list");
        DisplayTagUtils.of(request, buildingSearchRequest);
        if (SecurityUtils.getAuthorities().contains("ROLE_STAFF")) {
            Long staffId = SecurityUtils.getPrincipal().getId();
            buildingSearchRequest.setStaffId(staffId);
            List<BuildingSearchResponse> responseList = buildingService.findAll(buildingSearchRequest, PageRequest.of(buildingSearchRequest.getPage() - 1, buildingSearchRequest.getMaxPageItems()));
            buildingSearchRequest.setListResult(responseList);
        } else {
            List<BuildingSearchResponse> responseList = buildingService.findAll(buildingSearchRequest, PageRequest.of(buildingSearchRequest.getPage() - 1, buildingSearchRequest.getMaxPageItems()));
            buildingSearchRequest.setListResult(responseList);
        }
        mav.addObject("modelSearch", buildingSearchRequest);
        mav.addObject("listStaffs", userService.getStaffs());
        mav.addObject("districts", District.type());
        mav.addObject("typeCodes", TypeCode.type());
        return mav;
    }

    @RequestMapping(value = "/admin/building-edit", method = RequestMethod.GET)
    public ModelAndView buildingEdit(@ModelAttribute("buildingEdit") BuildingDTO buildingDTO, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("districts", District.type());
        mav.addObject("typeCodes", TypeCode.type());
        return mav;
    }


    @RequestMapping(value = "/admin/building-edit-{id}", method = RequestMethod.GET)
    public ModelAndView buildingEdit(@PathVariable("id") Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        //Xuong DB tim building theo id
        BuildingEntity building = buildingRepository.findById(id).get();
        BuildingDTO buildingDTO = buildingService.toBuildingDTO(building);
        mav.addObject("buildingEdit", buildingDTO);
        mav.addObject("districts", District.type());
        mav.addObject("typeCodes", TypeCode.type());
        return mav;
    }

}
