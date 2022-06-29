package cl.ccarrenov.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;
import cl.ccarrenov.mvc.model.Country;
import cl.ccarrenov.mvc.model.Region;

@Controller
public class IndexController {

    /* Load index.html */
    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("countries", countries());

        return "index"; //name view 
    }

    /* Ajax load regions */
    @RequestMapping(value = "/regions")
    @ResponseBody
    public List<Region> getRegions(@RequestParam String countryCode) {
        return regions(Integer.parseInt(countryCode));
    }

    /* Load static countries */
    public List<Country> countries(){
        List<Country> countries = new ArrayList<Country>();
        countries.add(new Country(1, "United States of America"));
        countries.add(new Country(56, "Chile"));
        return countries;
    }

    /* Load static regions from  country code*/
    public List<Region> regions(int countryCode){

        List<Region> regions = new ArrayList<Region>();

        if(countryCode == 1){
            regions.add(new Region(2, "California", 1));
            regions.add(new Region(3, "Texas", 1));
        }

        if(countryCode == 56){
            regions.add(new Region(13, "Metropolitana", 56));
            regions.add(new Region(1, "Arica Y Parinacota", 56));
        }

        return regions;
    }

}