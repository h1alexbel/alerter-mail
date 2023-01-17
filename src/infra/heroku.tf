resource "heroku_app" "alerter-mail" {
  name                  = "alerter-mail"
  region                = "us"
  sensitive_config_vars = {
    BOT_TOKEN         = var.bot_token
    BOT_MAIL_PASSWORD = var.bot_mail_password
  }
  config_vars = {
    BOT_MAIL_NAME = var.bot_mail_name
  }
}

variable "bot_token" {
  default = ""
}

variable "bot_mail_name" {
  default = ""
}

variable "bot_mail_password" {
  default = ""
}