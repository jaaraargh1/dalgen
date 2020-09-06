public class SymbolTable {
	private static final int INIT_CAPACITY = 7;

	/* Number of key-value pairs in the symbol table */
	private int N;
	/* Size of linear probing table */
	private int M;
	/* The keys */
	private String[] keys;
	/* The values */
	private Character[] vals;

	public SymbolTable() {
		this(INIT_CAPACITY);
	}

	public SymbolTable(int capacity) {
		N = 0;
		M = capacity;
		keys = new String[M];
		vals = new Character[M];
	}

	public int size() {
		return N;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Does a key-value pair with the given key exist in the symbol table?
	 */
	public boolean contains(String key) {
		return get(key) != null;
	}

	/**
	 * Hash function for keys - returns value between 0 and M-1
	 */
	public int hash(String key) {
		int i;
		int v = 0;

		for (i = 0; i < key.length(); i++) {
			v += key.charAt(i);
		}
		return v % M;
	}

	/**
	 * Insert the key-value pair into the symbol table
	 */
	public void put(String key, Character val) {
		int home = hash(key);
		int currentPosition = home;

		if (size() != M) {
			for (int i = 0; i < M; i++) {
				if (keys[currentPosition] == null) {
					keys[currentPosition] = key;
					vals[currentPosition] = val;
					N++;
					return;
				}
				currentPosition++;
				currentPosition %= M;

				if (i == M && keys[M] != null) {
					currentPosition = 0;

				}
			}

			keys[currentPosition] = key;
		} else {
			System.out.println("Hashmappen är full.");
		}
	}

	/**
	 * Return the value associated with the given key, null if no such value
	 */
	public Character get(String key) {
		Character val = null;
		int home = hash(key);
		int currentPosition = home;

		if (size() != 0) {
			for (int i = 0; i < M; i++) {
				if ((keys[currentPosition].contains(key))) {
					System.out.println("key" + (key));
					System.out.println(keys[currentPosition] + "nuvarande sökplats. ");
					return vals[currentPosition];
				}
				currentPosition++;
				currentPosition %= M;

				if (i == M && keys[M] != null) {
					currentPosition = 0;

				}
			}

		} else {
			System.out.println("Hashmappen är tom.");
		}

		return null;
	} // dummy code

	/**
	 * Delete the key (and associated value) from the symbol table
	 */
	public void delete(String key) {
		return;
	} // dummy code

	/**
	 * Print the contents of the symbol table
	 */
	public void dump() {
		String str = "";

		for (int i = 0; i < M; i++) {
			str = str + i + ". " + vals[i];
			if (keys[i] != null) {
				str = str + " " + keys[i] + " (";
				str = str + hash(keys[i]) + ")";
			} else {
				str = str + " -";
			}
			System.out.println(str);
			str = "";
		}
	}
}
