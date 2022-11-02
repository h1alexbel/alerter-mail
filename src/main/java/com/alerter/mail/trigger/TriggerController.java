package com.alerter.mail.trigger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1/trigger")
public class TriggerController {

  @GetMapping
  public final TrResponse triggerBot() {
    return new TrResponse("Triggered. You can get back to the Telegram!");
  }
}