package net.fiap.postech.fastburger.adapters.order;

public record PayMentProcess(String orderNumber, String type, boolean wasPayed) {
}
