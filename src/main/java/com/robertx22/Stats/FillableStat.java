package com.robertx22.stats;

public abstract class FillableStat extends Stat {

	public FillableStat() {
		this.CurrentValue = (int) this.Value;
	}

	private int CurrentValue;

	public int GetCurrentValue() {
		return CurrentValue;
	}

	public void Increase(int i) {
		CurrentValue += i;
		if (CurrentValue > this.Value) {
			CurrentValue = (int) Value;
		}
	}

	public void Decrease(int i) {
		CurrentValue -= i;
		if (CurrentValue < 0) {
			CurrentValue = 0;
		}
	}
}
