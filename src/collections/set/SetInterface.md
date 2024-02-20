###Set Implementations

The Set implementations are grouped into general-purpose and special-purpose implementations.

#####General-Purpose Set Implementations

There are three general-purpose Set implementations —
1. HashSet,
2. TreeSet, and 
3. LinkedHashSet. 

<code>HashSet</code> is much faster than <code>TreeSet</code> (constant-time versus log-time for most operations) but offers <b>no ordering guarantees</b>. 

If you need to use the operations in the <code>SortedSet</code> interface, or if value-ordered iteration is required, use <code>TreeSet</code>; otherwise, use <code>HashSet</code>. It's a fair bet that you'll end up using HashSet most of the time.

<code>LinkedHashSet</code> is in some sense intermediate between <code>HashSet</code> and <code>TreeSet</code>. Implemented as a hash table with a linked list running through it, it provides insertion-ordered iteration (least recently inserted to most recently) and runs nearly as fast as <code>HashSet</code>. The <code>LinkedHashSet</code> implementation spares its clients from the unspecified, generally chaotic ordering provided by <code>HashSet</code> without incurring the increased cost associated with <code>TreeSet</code>.

#####Special-Purpose Set Implementations

There are two special-purpose Set implementations — 
1. EnumSet and 
2. CopyOnWriteArraySet.


<code>EnumSet</code> is a high-performance Set implementation for enum types. All of the members of an enum set must be of the same enum type. Internally, it is represented by a bit-vector, typically a single long. Enum sets support iteration over ranges of enum types. For example, given the enum declaration for the days of the week, you can iterate over the weekdays. The <code>EnumSet</code> class provides a static factory that makes it easy.

    for (Day d : EnumSet.range(Day.MONDAY, Day.FRIDAY))
        System.out.println(d);
Enum sets also provide a rich, typesafe replacement for traditional bit flags.

    EnumSet.of(Style.BOLD, Style.ITALIC)
<code>CopyOnWriteArraySet</code> is a Set implementation backed up by a copy-on-write array. All mutative operations, such as add, set, and remove, are implemented by making a new copy of the array; no locking is ever required. Even iteration may safely proceed concurrently with element insertion and deletion. Unlike most Set implementations, the add, remove, and contains methods require time proportional to the size of the set. This implementation is only appropriate for sets that are rarely modified but frequently iterated. It is well suited to maintaining event-handler lists that must prevent duplicates.