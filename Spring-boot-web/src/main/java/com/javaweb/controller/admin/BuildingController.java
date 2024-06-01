package com.javaweb.controller.admin;



import com.javaweb.entity.BuildingEntity;
import com.javaweb.enums.districtCode;
import com.javaweb.enums.typeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController(value="buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    private IUserService userService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private BuildingRepository buildingRepository;

    @RequestMapping (value = "/admin/building-list", method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute("modelSearch")BuildingSearchRequest buildingSearchRequest) {
        ModelAndView mav = new ModelAndView("admin/building/list");

        List<BuildingSearchResponse> responseList = buildingService.findAll(buildingSearchRequest);
        buildingSearchRequest.setListResult(responseList);

        mav.addObject("modelSearch",buildingSearchRequest);
        mav.addObject("staffs", userService.getStaffs());
        mav.addObject("district", districtCode.district());
        mav.addObject("typeCodes", typeCode.getTypeCode());

        return mav;
    }


    @GetMapping(value = "/admin/building-edit")
    public ModelAndView addBuilding(@ModelAttribute("buildingEdit")BuildingDTO buildingDTO) {
        ModelAndView mav = new ModelAndView("admin/building/edit");

        mav.addObject("district", districtCode.district());
        mav.addObject("typeCodes", typeCode.getTypeCode());

        return mav;
    }

    @GetMapping(value = "/admin/building-edit-{id}")
    public ModelAndView addBuilding(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("admin/building/edit");

        BuildingEntity buildingEntity = buildingRepository.findById(id).get();
        BuildingDTO buildingDTO = buildingService.toBuildingDTO(buildingEntity);
        mav.addObject("buildingEdit", buildingDTO);

        mav.addObject("district", districtCode.district());
        mav.addObject("typeCodes", typeCode.getTypeCode());

        return mav;
    }
}
