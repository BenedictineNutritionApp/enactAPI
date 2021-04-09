package enactApp.enactAPI.web.controller;

import enactApp.enactAPI.data.model.*;
import enactApp.enactAPI.data.repository.*;
import enactApp.enactAPI.data.translator.FoodLogEntryTranslator;
import enactApp.enactAPI.data.translator.FoodTranslator;
import enactApp.enactAPI.data.translator.UserTranslator;
import enactApp.enactAPI.web.models.FoodLogEntryView;
import enactApp.enactAPI.web.models.FoodView;
import enactApp.enactAPI.web.models.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/base")
	@PreAuthorize("hasRole('BASE') or hasRole('SUPER') or hasRole('MASTER')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/super")
	@PreAuthorize("hasRole('SUPER') or hasRole('MASTER')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/master")
	@PreAuthorize("hasRole('MASTER')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
