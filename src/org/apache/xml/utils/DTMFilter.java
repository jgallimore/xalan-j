package org.apache.xml.utils;

/**
 * Simple filter for doing node tests.  Note the semantics of this are
 * somewhat different that the DOM's NodeFilter.
 */
public interface DTMFilter
{

  // Constants for whatToShow.  These are used to set the node type that will 
  // be traversed. These values may be ORed together before being passed to
  // the DTMIterator.

  /**
   * Show all <code>Nodes</code>.
   */
  public static final int SHOW_ALL = 0xFFFFFFFF;

  /**
   * Show <code>Element</code> nodes.
   */
  public static final int SHOW_ELEMENT = 0x00000001;

  /**
   * Show <code>Attr</code> nodes. This is meaningful only when creating an
   * iterator or tree-walker with an attribute node as its
   * <code>root</code>; in this case, it means that the attribute node
   * will appear in the first position of the iteration or traversal.
   * Since attributes are never children of other nodes, they do not
   * appear when traversing over the main document tree.
   */
  public static final int SHOW_ATTRIBUTE = 0x00000002;

  /**
   * Show <code>Text</code> nodes.
   */
  public static final int SHOW_TEXT = 0x00000004;

  /**
   * Show <code>CDATASection</code> nodes.
   */
  public static final int SHOW_CDATA_SECTION = 0x00000008;

  /**
   * Show <code>EntityReference</code> nodes. Note that if Entity References
   * have been fully expanded while the tree was being constructed, these
   * nodes will not appear and this mask has no effect.
   */
  public static final int SHOW_ENTITY_REFERENCE = 0x00000010;

  /**
   * Show <code>Entity</code> nodes. This is meaningful only when creating
   * an iterator or tree-walker with an<code> Entity</code> node as its
   * <code>root</code>; in this case, it means that the <code>Entity</code>
   *  node will appear in the first position of the traversal. Since
   * entities are not part of the document tree, they do not appear when
   * traversing over the main document tree.
   */
  public static final int SHOW_ENTITY = 0x00000020;

  /**
   * Show <code>ProcessingInstruction</code> nodes.
   */
  public static final int SHOW_PROCESSING_INSTRUCTION = 0x00000040;

  /**
   * Show <code>Comment</code> nodes.
   */
  public static final int SHOW_COMMENT = 0x00000080;

  /**
   * Show <code>Document</code> nodes. (Of course, as with Attributes
   * and such, this is meaningful only when the iteration root is the
   * Document itself, since Document has no parent.)
   */
  public static final int SHOW_DOCUMENT = 0x00000100;

  /**
   * Show <code>DocumentType</code> nodes. 
   */
  public static final int SHOW_DOCUMENT_TYPE = 0x00000200;

  /**
   * Show <code>DocumentFragment</code> nodes. (Of course, as with
   * Attributes and such, this is meaningful only when the iteration
   * root is the Document itself, since DocumentFragment has no parent.)
   */
  public static final int SHOW_DOCUMENT_FRAGMENT = 0x00000400;

  /**
   * Show <code>Notation</code> nodes. This is meaningful only when creating
   * an iterator or tree-walker with a <code>Notation</code> node as its
   * <code>root</code>; in this case, it means that the
   * <code>Notation</code> node will appear in the first position of the
   * traversal. Since notations are not part of the document tree, they do
   * not appear when traversing over the main document tree.
   */
  public static final int SHOW_NOTATION = 0x00000800;
  
  /**

   * This bit instructs the iterator to show namespace nodes, which
   * are modeled by DTM but not by the DOM.  Make sure this does not
   * conflict with {@link org.w3c.dom.traversal.NodeFilter}.
   * <p>
   * ISSUE: Might be safer to start from higher bits and work down,
   * to leave room for the DOM to expand its set of constants... Or,
   * possibly, to create a DTM-specific field for these additional bits.
   */
  public static final int SHOW_NAMESPACE = 0x00001000;

  /**
   * Special bit for filters implementing match patterns starting with
   * a function.  Make sure this does not conflict with
   * {@link org.w3c.dom.traversal.NodeFilter}.
   * <p>
   * ISSUE: Might be safer to start from higher bits and work down,
   * to leave room for the DOM to expand its set of constants... Or,
   * possibly, to create a DTM-specific field for these additional bits.
   */
  public static final int SHOW_BYFUNCTION = 0x00010000;

  /**
   * Test whether a specified node is visible in the logical view of a
   * <code>DTMIterator</code>. Normally, this function
   * will be called by the implementation of <code>DTMIterator</code>; 
   * it is not normally called directly from
   * user code.
   * 
   * @param nodeHandle int Handle of the node.
   * @param whatToShow one of SHOW_XXX values.
   * @return one of FILTER_ACCEPT, FILTER_REJECT, or FILTER_SKIP.
   */
  public short acceptNode(int nodeHandle, int whatToShow);
  
  /**
   * Test whether a specified node is visible in the logical view of a
   * <code>DTMIterator</code>. Normally, this function
   * will be called by the implementation of <code>DTMIterator</code>; 
   * it is not normally called directly from
   * user code.
   * <p>
   * Issue: Is 0xFFFF really better than '*'? 
   * <p>
   * Issue: Should this be setNameMatch(expandedName) followed by
   * normal accept()? Or will we really be testing a different name at
   * every invocation?
   * 
   * @param nodeHandle int Handle of the node.
   * @param whatToShow one of SHOW_XXX values.
   * @param expandedName a value defining the exanded name as defined in 
   *                     the DTM interface.  Wild cards will be defined 
   *                     by 0xFFFF in the high word and/or in the low word.
   * @return one of FILTER_ACCEPT, FILTER_REJECT, or FILTER_SKIP.  */
  public short acceptNode(int nodeHandle, int whatToShow, int expandedName);
 
}
