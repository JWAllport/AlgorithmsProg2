# AlgorithmsProg2

Problem
Want	to	compete	in	the	Riddle	Olympics?	Then	you	had	better	know	how	to	keep	score!
Contestants	are	ranked	first	by	the	number	of	riddles	solved	(the	more	the	better),
then	by	decreasing	amounts	of	penalty	time.	If	two	or	more	contestants	are	tied	in
both	riddles	solved	and	penalty	time,	they	are	displayed	in	order	of	increasing	team
numbers.
A	riddle	is	considered	solved	by	a	contestant	if	any	of	the	submissions	for	that riddle	was	
judged	correct.	Penalty	time	is	computed	as	the	number	of	minutes	it took	until	the	first	
correct	submission	for	a	riddle	was	received,	plus	5	minutes	for each	incorrect	submission	
prior	to	the	correct	solution	(you	may	assume	that	no	contestant	submits	a	solution	for	the	
same	riddle	after	getting	it	correct).	Unsolved	riddles	incur	no time	penalties.
Input
The	input	consists	of	a	snapshot	of	the	judging	queue,	containing	entries	from	some	or	all	
of	contestants	solving	riddles.	Each	line	of	input consists	of	three	numbers	(max	value	of	
2^31-1,	i.e.	a	32-bit	integer)	and	a	letter	in	the	format:	contestant	riddle	time	L.	Where L	
can	be	C,	I,	R,	U,	or	E.	These	stand	for	Correct,	Incorrect,	clarification	Request, Unjudged,	
and	Erroneous	submission.	The	last	three	cases	do	not	affect	scoring. The	lines	of	input	
appear	in	the	order	in	which	the	submissions	were	received.
Output
The	output	for	each	test	case	will	consist	of	a	scoreboard,	sorted	by	the	criteria	described
above.	Each	line	of	output	will	contain	a	contestant	number,	the	number	of	riddles
solved	by	the	contestant	and	the	total	time	penalty	accumulated	by	the	contestant.
Since	not	all	contestants	are	actually	participating,	only	display	those	contestants	who
have	made	a	submission.
Sample	"input.txt"
1	2	10	I
3	1	11	C
1	2	19	R
1	2	21	C
1	1	25	C
Expected	output	for	above	(written	to	"output.txt")
1	2	51
3	1	11
