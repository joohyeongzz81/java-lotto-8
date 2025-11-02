package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> result;

    public LottoResult() {
        this.result = new EnumMap<>(Rank.class);
        initializeResult();
    }

    private void initializeResult() {
        for (Rank rank : Rank.values()) {
            if (rank != Rank.NONE) {
                result.put(rank, 0);
            }
        }
    }

    public void add(Rank rank) {
        if (rank == Rank.NONE) {
            return;
        }
        result.put(rank, result.get(rank) + 1);
    }

    public int getCount(Rank rank) {
        return result.getOrDefault(rank, 0);
    }

    public double calculateProfitRate(int totalAmount) {
        long totalPrize = calculateTotalPrize();
        return (double) totalPrize / totalAmount * 100;
    }

    private long calculateTotalPrize() {
        return result.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getPrize() * entry.getValue())
                .sum();
    }
}