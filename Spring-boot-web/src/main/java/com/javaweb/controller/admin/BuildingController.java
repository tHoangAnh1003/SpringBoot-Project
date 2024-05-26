package com.javaweb.controller.admin;



import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.enums.districtCode;
import com.javaweb.enums.typeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.BuildingService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController(value="buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    IUserService userService;

    @Autowired
    BuildingService buildingService;

    @GetMapping(value = "admin/building-list")
    public ModelAndView buildingList(@ModelAttribute("modelSearch") BuildingSearchBuilder buildingSearchBuilder ) {
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("staffs", userService.getStaffs());
        mav.addObject("district", districtCode.district());
        mav.addObject("typeCodes", typeCode.getTypeCode());

        // Them tu SQL

        List<BuildingSearchResponse> result = buildingService.searchAll(buildingSearchBuilder);
//        BuildingSearchResponse buildingSearchResponse = new BuildingSearchResponse();
//
//        buildingSearchResponse.setId(1L);
//        buildingSearchResponse.setName("Python Building");
//        buildingSearchResponse.setAddress("105 Doãn Kế Thiện, Mai Dịch, Cầu Giấy");
//        buildingSearchResponse.setManagerName("Trần Hoàng Anh");
//        buildingSearchResponse.setManagerPhone("0337458786");
//
//        result.add(buildingSearchResponse);

        mav.addObject("buildings", result);

        return mav;
    }


    @GetMapping(value = "admin/building-edit")
    public ModelAndView addBuilding(@ModelAttribute("buildingEdit")BuildingDTO buildingDTO) {
        ModelAndView mav = new ModelAndView("admin/building/edit");

        mav.addObject("district", districtCode.district());
        mav.addObject("typeCodes", typeCode.getTypeCode());

        return mav;
    }

    @GetMapping(value = "admin/building-edit-{id}")
    public ModelAndView addBuilding(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("admin/building/edit");

        mav.addObject("district", districtCode.district());
        mav.addObject("typeCodes", typeCode.getTypeCode());

        // findBuilding them tu SQL

        BuildingDTO buildingDTO = buildingService.findBuildingById(id);
//        buildingDTO.setId(id);
//        buildingDTO.setName("Python Building");
//        buildingDTO.setDistrict("QUAN_11");
//        buildingDTO.setWard("Phường Mai Dịch");
//        buildingDTO.setStreet("Doãn Kế Thiện");
//        buildingDTO.setNumberOfBasement(3L);
//        List<String> tCode = new ArrayList<>();
//        tCode.add("NGUYEN_CAN");
//        buildingDTO.setTypeCode(tCode);

        mav.addObject("buildingEdit", buildingDTO);

        return mav;
    }
}
