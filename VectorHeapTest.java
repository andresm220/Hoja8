import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.NoSuchElementException;

/**
 * Pruebas unitarias para la clase VectorHeap.
 */
public class VectorHeapTest {
    private VectorHeap<Integer> heap;

    /**
     * Preparación antes de cada prueba.
     */
    @Before
    public void setUp() {
        heap = new VectorHeap<>();
    }

    /**
     * Verifica que los elementos se añadan correctamente y que el tamaño de la cola sea correcto.
     */
    @Test
    public void testAddAndSize() {
        assertEquals(0, heap.size());
        assertTrue(heap.isEmpty());

        heap.add(10);
        heap.add(20);
        heap.add(5);

        assertEquals(3, heap.size());
        assertFalse(heap.isEmpty());
    }

    /**
     * Verifica que peek() devuelva el elemento en la parte superior del montículo sin eliminarlo.
     */
    @Test
    public void testPeek() {
        assertNull(heap.peek());

        heap.add(10);
        heap.add(20);
        heap.add(5);

        assertEquals(Integer.valueOf(5), heap.peek());
    }

    /**
     * Verifica que poll() devuelva y elimine el elemento en la parte superior del montículo.
     */
    @Test
    public void testPoll() {
        assertNull(heap.poll());

        heap.add(10);
        heap.add(20);
        heap.add(5);

        assertEquals(Integer.valueOf(5), heap.poll());
        assertEquals(2, heap.size());
    }

    /**
     * Verifica que remove() arroje una excepción NoSuchElementException cuando la cola esté vacía.
     */
    @Test(expected = NoSuchElementException.class)
    public void testRemoveEmptyHeap() {
        heap.remove();
    }

    /**
     * Verifica que clear() elimine todos los elementos de la cola.
     */
    @Test
    public void testClear() {
        heap.add(10);
        heap.add(20);
        heap.add(5);

        heap.clear();

        assertEquals(0, heap.size());
        assertTrue(heap.isEmpty());
    }
}
