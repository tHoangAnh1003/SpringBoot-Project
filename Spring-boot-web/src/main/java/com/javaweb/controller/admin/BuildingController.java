package com.javaweb.controller.admin;



import com.javaweb.enums.districtCode;
import com.javaweb.enums.typeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.BuildingService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController(value="buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    IUserService userService;

    @Autowired
    BuildingService buildingService;

    @RequestMapping (value = "/admin/building-list", method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute("modelSearch")BuildingSearchRequest buildingSearchRequest) {
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("staffs", userService.getStaffs());
        mav.addObject("district", districtCode.district());
        mav.addObject("typeCodes", typeCode.getTypeCode());

        // Them tu SQL

//        List<BuildingSearchResponse> result = new ArrayList<>();
//        BuildingSearchResponse buildingSearchResponse = new BuildingSearchResponse();
//
//        buildingSearchResponse.setId(1L);
//        buildingSearchResponse.setName("Python Building");
//        buildingSearchResponse.setAddress("105 Doãn Kế Thiện, Mai Dịch, Cầu Giấy");
//        buildingSearchResponse.setManagerName("Trần Hoàng Anh");
//        buildingSearchResponse.setManagerPhone("0337458786");
//
//        result.add(buildingSearchResponse);

//        mav.addObject("buildings", result);

        Map<String, Object> ob = new HashMap<>();
        ob.put("name", buildingSearchRequest.getName());
        ob.put("district", buildingSearchRequest.getDistrict());
        ob.put("ward", buildingSearchRequest.getWard());
        ob.put("street", buildingSearchRequest.getStreet());
        ob.put("numberOfBasement", buildingSearchRequest.getNumberOfBasement());
        ob.put("direction", buildingSearchRequest.getDirection());
        ob.put("level", buildingSearchRequest.getLevel());
        ob.put("areaFrom", buildingSearchRequest.getAreaFrom());
        ob.put("areaTo", buildingSearchRequest.getAreaTo());
        ob.put("rentPriceFrom", buildingSearchRequest.getRentPriceFrom());
        ob.put("rentPriceTo", buildingSearchRequest.getRentPriceTo());
        ob.put("managerName", buildingSearchRequest.getManagerName());
        ob.put("managerPhone", buildingSearchRequest.getManagerPhone());
        ob.put("staffId", buildingSearchRequest.getStaffId());

        List<String> typeCode = buildingSearchRequest.getTypeCode();

        List<BuildingSearchResponse> result = buildingService.findAll(ob, typeCode);

        mav.addObject("buildings", result);

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
    public ModelAndView addBuilding(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("admin/building/edit");

        mav.addObject("district", districtCode.district());
        mav.addObject("typeCodes", typeCode.getTypeCode());

        // findBuilding them tu SQL

        BuildingDTO buildingDTO = new BuildingDTO();
        buildingDTO.setId(id);
        buildingDTO.setName("Python Building");
        buildingDTO.setDistrict("QUAN_11");
        buildingDTO.setWard("Phường Mai Dịch");
        buildingDTO.setStreet("Doãn Kế Thiện");
        buildingDTO.setNumberOfBasement(3L);
        List<String> tCode = new ArrayList<>();
        tCode.add("NGUYEN_CAN");
        buildingDTO.setTypeCode(tCode);

        mav.addObject("buildingEdit", buildingDTO);

        return mav;
    }
}
