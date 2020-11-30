package com.CarSaleWebsite.Kolesa.Controllers;

import com.CarSaleWebsite.Kolesa.Models.DiningTableTrack;
import com.CarSaleWebsite.Kolesa.Models.enums.OrderStatus;
import com.CarSaleWebsite.Kolesa.Repositories.DiningTableTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/table")
public class TrackController {
    @Autowired
    private final DiningTableTrackRepository diningTableTrackRepository;

    public TrackController(DiningTableTrackRepository diningTableTrackRepository) {
        this.diningTableTrackRepository = diningTableTrackRepository;
    }


    @GetMapping("/track")
    public String getDoneOrders(Model model){
        List<DiningTableTrack> diningTableTracks
                =diningTableTrackRepository.findAllByOrderidStatus(OrderStatus.DONE.name());
        model.addAttribute("tables",diningTableTracks);
        return "table-track-page";
    }
}
